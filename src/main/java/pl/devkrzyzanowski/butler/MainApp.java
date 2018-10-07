/*
 * Copyright (C) 2018 Michal Krzyzanowski
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package pl.devkrzyzanowski.butler;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.stage.Stage;
import pl.devkrzyzanowski.butler.Model.Database;
import pl.devkrzyzanowski.butler.menager.StageManager;

/** starting class */
public class MainApp extends Application {
    /**  */
    public static StageManager stageManager;
    /**  */
    public static Database databaseManager;
 
    @Override
    public void start(Stage stage) throws Exception {
        stageManager = new StageManager(stage);
        databaseManager = new Database();
        stageManager.newStage("/fxml/loginPage.fxml");
    }
    
    /**
     * The main() method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application can not be
     * launched through deployment artifacts, e.g., in IDEs with limited FX
     * support. NetBeans ignores main().
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
