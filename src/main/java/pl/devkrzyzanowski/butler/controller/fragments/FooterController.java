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
package pl.devkrzyzanowski.butler.controller.fragments;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import pl.devkrzyzanowski.butler.Model.Database;

/**
 *
 * @author Michal Krzyzanowski
 * 
 */
public class FooterController {
    
    @FXML Label nameOfConnectedDB, nameOfUserDB;
    
    private Database db;
    

    
}
