/**
 *  Plugin UltraHardcore Reloaded (UHPlugin)
 *  Copyright (C) 2013 azenet
 *  Copyright (C) 2014-2015 Amaury Carrade
 *
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see [http://www.gnu.org/licenses/].
 */
package eu.carrade.amaury.UHCReloaded.borders.shapes;

import org.bukkit.Location;
import org.bukkit.World;


public class CircularMapShape implements MapShapeDescriptor {

	/**
	 * Returns true if the given location is inside the map.
	 *
	 * @param location The location to check.
	 * @param diameter The diameter of the map.
	 * @param center   The center of the map.
	 *
	 * @return {@code true} if the given location is inside the map.
	 */
	@Override
	public boolean isInsideBorder(final Location location, final Double diameter, final Location center) {
		Location centerRef = center.clone();
		centerRef.setY(location.getY());

		return (location.distance(centerRef) <= Math.floor(diameter / 2));
	}

	/**
	 * Returns the distance between the given location and the border with this diameter.
	 *
	 * @param location The distance will be calculated between this location and the closest point of the border.
	 * @param diameter The diameter of the border.
	 * @param center   The center of the border.
	 *
	 * @return The distance between the given {@code location} and the closest point of the border.<br />
	 * {@code -1} if the location is inside the border.
	 */
	@Override
	public double getDistanceToBorder(final Location location, final Double diameter, final Location center) {
		if(!location.getWorld().getEnvironment().equals(World.Environment.NORMAL)) { // The nether/end are not limited.
			return -1;
		}

		if(isInsideBorder(location, diameter, center)) {
			return -1;
		}

		Location centerRef = center.clone();
		centerRef.setY(location.getY());

		return (location.distance(centerRef) - Math.floor(diameter/2));
	}
}
