/*******************************************************************************
 * Copyright (c) 2016 IBM Corp.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/ 
package application.rest;

import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

@Path("/events")
public class LibertyRestEndpoint extends Application {

    @POST
    @Produces("text/plain")
    @Consumes("application/json")
    public Response event(String json) {
    	
    	JsonReader r = Json.createReader(new StringReader(json));
    	JsonObject arg = r.readObject();
    	
    	switch(arg.getString("type")){
    		case "url_verification":{
    			return Response.ok(arg.getString("challenge")).build();
    		}
    		default:{
    			System.out.println("Unknown event type : "+arg.getString("type"));
    			System.out.println("Full JSON: "+ arg.toString());
    		}
    	}
    	
    	
        return Response.status(Status.NOT_FOUND).build();
    }

}