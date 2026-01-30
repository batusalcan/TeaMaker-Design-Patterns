import controller.TeaMakerController;
import model.TeaMakerModel;

public class TeaMakerTestDrive {
    public static void main(String[] args) {
        TeaMakerModel model = new TeaMakerModel();
        TeaMakerController controller = new TeaMakerController(model);
    }
}
