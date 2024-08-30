/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership. The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package com.example.jaxrs.graalvm;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/")
@Produces(MediaType.APPLICATION_XML)
public class CustomerResource {
    private final AtomicInteger id = new AtomicInteger();
    private final Map<Long, Customer> customers = new HashMap<>();

    @GET
    @Path("/customers")
    public Collection<Customer> getCustomers() {
        return customers.values();
    }

    @GET
    @Path("/customers/{id}")
    public Response getCustomer(@PathParam("id") long id) {
        final Customer customer = customers.get(id);
        if (customer != null) {
            return Response.ok(customer).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @POST
    @Path("/customers")
    public Response addCustomer(Customer customer) {
        customer.setId(id.incrementAndGet());
        customers.put(customer.getId(), customer);
        return Response.ok(customer).build();
    }

    @DELETE
    @Path("/customers/{id}")
    public Response deleteCustomer(@PathParam("id") long id) {
        if (customers.remove(id) != null) {
            return Response.noContent().build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }
}
