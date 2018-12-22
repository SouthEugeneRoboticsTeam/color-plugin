@file:JvmName("ColorWidget")

package org.sert2521.color

import edu.wpi.first.shuffleboard.api.widget.Description
import edu.wpi.first.shuffleboard.api.widget.ParametrizedController
import edu.wpi.first.shuffleboard.api.widget.SimpleAnnotatedWidget
import javafx.beans.property.SimpleIntegerProperty
import javafx.fxml.FXML
import javafx.scene.canvas.Canvas
import javafx.scene.layout.Pane
import javafx.scene.paint.Color
import kotlin.math.absoluteValue

@Description(
        name = "Color Widget",
        summary = "Displays team color.",
        dataTypes = [String::class]
)
@ParametrizedController(value = "ColorWidget.fxml")
class ColorWidget : SimpleAnnotatedWidget<String>() {

    private var color: String? = null

    @FXML
    private lateinit var root: Pane

    @FXML
    private lateinit var canvas: Canvas

    @FXML
    private fun initialize() {
        root.widthProperty().addListener { _, _, newValue: Number ->
            canvas.width = newValue.toDouble()
            generateLayover()
        }
        root.heightProperty().addListener { _, _, newValue: Number ->
            canvas.height = newValue.toDouble()
            generateLayover()
        }

        dataProperty().addListener { _, _, colorStr -> color = colorStr }

        generateLayover()
    }

    private fun generateLayover() {
        val gc = canvas.graphicsContext2D

        clear()

        if (color != null) {
            gc.fill = Color.web(color)
            gc.fillRect(0.0, 0.0, canvas.width, canvas.height)
        }
    }

    private fun clear() = canvas.graphicsContext2D.clearRect(0.0, 0.0, canvas.width, canvas.height)

    override fun getView() = root
}
