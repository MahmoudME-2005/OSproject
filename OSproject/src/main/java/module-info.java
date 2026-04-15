module mahmoudehabmoheb.osprojectnew {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens mahmoudehabmoheb.osprojectnew to javafx.fxml;
    exports mahmoudehabmoheb.osprojectnew;
}
