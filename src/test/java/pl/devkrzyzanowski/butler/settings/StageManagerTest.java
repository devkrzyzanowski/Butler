/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.devkrzyzanowski.butler.settings;

import javafx.stage.Stage;
import javafx.stage.Window;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author MichalKrzyzanowski
 */
public class StageManagerTest {
    
    public StageManagerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of newStage method, of class StageManager.
     */
    @Test
    public void testNewStage() {
        System.out.println("newStage");
        Stage stage = null;
        String fxml = "";
        StageManager instance = new StageManager();
        instance.newStage(stage, fxml);
    }

    /**
     * Test of changeStage method, of class StageManager.
     */
    @Test
    public void testChangeStage() {
        System.out.println("changeStage");
        Stage stage = null;
        String fxml = "";
        StageManager instance = new StageManager();
        instance.changeStage(stage, fxml);
    }

    /**
     * Test of addModalStage method, of class StageManager.
     */
    @Test
    public void testAddModalStage() {
        System.out.println("addModalStage");
        Window owner = null;
        String fxml = "";
        StageManager instance = new StageManager();
        instance.addModalStage(owner, fxml);
    }
    
}
