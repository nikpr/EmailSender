package view.status;

import javafx.scene.paint.Color;

/**
 *
 * @author Nik
 */
enum StatusColors {
    Error {
        @Override
        public Color getColor() {
            return Color.RED;
        }
    },
    Success {
        @Override
        public Color getColor() {
            return Color.BLUE;
        }
    };
abstract Color getColor();
}