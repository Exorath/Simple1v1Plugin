/*
 * Copyright 2017 Exorath
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.exorath.plugin.simple1v1.core.state;

import org.bukkit.Bukkit;

/**
 * Created by toonsev on 2/5/2017.
 */
public class StateManager {
    private State state = State.WAITING_FOR_PLAYERS;

    public State getState() {
        return state;
    }

    public void setState(State state) {
        if(this.state == state)
            return;
        State oldState = state;
        this.state = state;
        StateChangeEvent stateChangeEvent = new StateChangeEvent(oldState, state);
        Bukkit.getPluginManager().callEvent(stateChangeEvent);
    }
}
