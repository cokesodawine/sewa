package com.example.vehicle.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet ( name = "test-controller", urlPatterns = "/test")
public class TestingController extends HttpServlet{

    // No Args Constructor
    public TestingController() {}

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
