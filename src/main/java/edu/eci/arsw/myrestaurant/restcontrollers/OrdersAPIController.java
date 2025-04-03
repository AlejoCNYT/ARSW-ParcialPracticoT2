/*
 * Copyright (C) 2016 Pivotal Software, Inc.
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
package edu.eci.arsw.myrestaurant.restcontrollers;

import com.fasterxml.jackson.databind.util.JSONPObject;
import edu.eci.arsw.myrestaurant.services.RestaurantOrderServices;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import net.minidev.json.JSONArray;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * @author hcadavid
 */
@RestController(value = "/orders")
public class OrdersAPIController
{
    public ResponseEntity<?> OrdersAPIController()
    {

        RestaurantOrderServices ros = null;
        JSONArray result = new JSONArray();

        try
        {

            for (Integer orderId : ros.getTablesWithOrders())
            {

                if (!ros.getTablesWithOrders().isEmpty())
                {
                    JSONPObject json = new JSONPObject("totalOrder", ros.getTableOrder(orderId));
                    result.add(json);
                }

            }
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>("Error 404", HttpStatus.NOT_FOUND);
        }

    }

}
