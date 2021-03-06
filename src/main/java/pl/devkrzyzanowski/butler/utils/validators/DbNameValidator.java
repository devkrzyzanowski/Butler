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
package pl.devkrzyzanowski.butler.utils.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Michal Krzyzanowski
 */
public class DbNameValidator implements IValidator{
    
    private final Pattern pattern;
    private Matcher matcher;
    
    /** pattern for database name validate */
    private static final String PATTERN = 
            "[A-Za-z]{4,20}";
    
    /** init class */
    public DbNameValidator() {
        pattern = Pattern.compile(PATTERN);
    }
    
    @Override
    public boolean validate(final String data) {
        matcher = pattern.matcher(data);
        return matcher.matches();
    }
    
}
