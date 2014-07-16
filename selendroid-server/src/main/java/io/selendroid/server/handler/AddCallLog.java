/*
 * Copyright 2014 eBay Software Foundation and selendroid committers.
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package io.selendroid.server.handler;

import io.selendroid.server.RequestHandler;
import io.selendroid.server.Response;
import io.selendroid.server.SelendroidResponse;
import io.selendroid.server.model.DefaultSelendroidDriver;
import io.selendroid.util.SelendroidLogger;

import org.json.JSONException;
import org.json.JSONObject;
import org.webbitserver.HttpRequest;

import android.util.Log;

public class AddCallLog extends RequestHandler {

  public AddCallLog(String mappedUri) {
    super(mappedUri);
  }

  @Override
  public Response handle(HttpRequest request) throws JSONException {
    SelendroidLogger.info("add call log");
    Log.d("TAG", "About to add a call log.");
    JSONObject parameters = getPayload(request).getJSONObject("parameters");
    String number = parameters.getString("number");
    int duration = Integer.parseInt(parameters.getString("duration"));
    ((DefaultSelendroidDriver)getSelendroidDriver(request)).addCallLog(number, duration);
    return new SelendroidResponse(getSessionId(request), "added record to phone logs");
  }
  
}
