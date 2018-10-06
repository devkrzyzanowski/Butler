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

import pl.devkrzyzanowski.butler.controller.MainPageController;

/**
 *
 * @author Michal Krzyzanowski
 */
public class MainPageFragment {
    
    /** a parent class using this controller to controll content  */
    private final MainPageController mainPageController;    
    
    /** init MainPageFragment with the use class
     * @param mainPageController parent class which using this controller
     */    
    public MainPageFragment(MainPageController mainPageController) {
        this.mainPageController = mainPageController;
    }
    
    /**
     *
     * @return mainPageController
     */
    protected MainPageController getMainPageController() {
        return mainPageController;
    }
}
