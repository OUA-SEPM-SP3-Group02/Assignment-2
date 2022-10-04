package com.sepm.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class View {
    private final BufferedReader br;

    public View() {
        this.br = new BufferedReader(new InputStreamReader(System.in));
    }

    protected String getUserInput() {
        String input = null;
        try {
            input = this.br.readLine();
        } catch (IOException e) {

        }
        return input;
    }


}
