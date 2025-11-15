package com.histora.app.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import com.histora.app.R
import com.histora.app.models.CausalNode
import com.histora.app.models.NodeType

class CausalityGraphView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private var nodes: List<CausalNode> = emptyList()
    private val nodePaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val textPaint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val linePaint = Paint(Paint.ANTI_ALIAS_FLAG)

    private val nodeRadius = 60f
    private var selectedNode: CausalNode? = null

    init {
        setupPaints()
    }

    private fun setupPaints() {
        nodePaint.style = Paint.Style.FILL

        textPaint.color = Color.WHITE
        textPaint.textSize = 24f
        textPaint.textAlign = Paint.Align.CENTER

        linePaint.color = context.getColor(R.color.text_hint)
        linePaint.strokeWidth = 4f
        linePaint.style = Paint.Style.STROKE
    }

    fun setNodes(nodes: List<CausalNode>) {
        this.nodes = nodes
        layoutNodes()
        invalidate()
    }

    private fun layoutNodes() {
        if (nodes.isEmpty()) return

        val width = measuredWidth.toFloat()
        val height = measuredHeight.toFloat()

        // Simple auto-layout: arrange nodes in a grid
        val cols = Math.ceil(Math.sqrt(nodes.size.toDouble())).toInt()
        val rows = Math.ceil(nodes.size.toDouble() / cols).toInt()

        val xSpacing = width / (cols + 1)
        val ySpacing = height / (rows + 1)

        nodes.forEachIndexed { index, node ->
            val col = index % cols
            val row = index / cols

            // Update node position
            val updatedNode = node.copy(
                x = xSpacing * (col + 1),
                y = ySpacing * (row + 1)
            )
            nodes = nodes.toMutableList().apply {
                set(index, updatedNode)
            }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if (nodes.isNotEmpty()) {
            layoutNodes()
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (nodes.isEmpty()) {
            drawEmptyState(canvas)
            return
        }

        // Draw connections first
        drawConnections(canvas)

        // Draw nodes
        drawNodes(canvas)
    }

    private fun drawConnections(canvas: Canvas) {
        nodes.forEach { node ->
            node.connectedNodes.forEach { targetId ->
                val targetNode = nodes.find { it.id == targetId }
                targetNode?.let {
                    canvas.drawLine(
                        node.x, node.y,
                        it.x, it.y,
                        linePaint
                    )
                }
            }
        }
    }

    private fun drawNodes(canvas: Canvas) {
        nodes.forEach { node ->
            // Set color based on node type
            nodePaint.color = getNodeColor(node.type)

            // Draw node circle
            canvas.drawCircle(node.x, node.y, nodeRadius, nodePaint)

            // Draw node title (abbreviated)
            val title = if (node.title.length > 6) {
                node.title.substring(0, 6) + "..."
            } else {
                node.title
            }

            canvas.drawText(
                title,
                node.x,
                node.y + textPaint.textSize / 3,
                textPaint
            )
        }
    }

    private fun getNodeColor(type: NodeType): Int {
        return when (type) {
            NodeType.CAUSE -> context.getColor(R.color.timeline_original)
            NodeType.EVENT -> context.getColor(R.color.primary)
            NodeType.RESULT -> context.getColor(R.color.success)
            NodeType.PERSON -> context.getColor(R.color.accent)
            NodeType.DECISION -> context.getColor(R.color.warning)
        }
    }

    private fun drawEmptyState(canvas: Canvas) {
        val emptyTextPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
            color = context.getColor(R.color.text_hint)
            textSize = 32f
            textAlign = Paint.Align.CENTER
        }

        canvas.drawText(
            "暂无因果关系图",
            width / 2f,
            height / 2f,
            emptyTextPaint
        )
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            val touchedNode = findNodeAt(event.x, event.y)
            if (touchedNode != null) {
                selectedNode = touchedNode
                // TODO: Show node details dialog
                return true
            }
        }
        return super.onTouchEvent(event)
    }

    private fun findNodeAt(x: Float, y: Float): CausalNode? {
        return nodes.find { node ->
            val dx = x - node.x
            val dy = y - node.y
            Math.sqrt((dx * dx + dy * dy).toDouble()) <= nodeRadius
        }
    }
}
