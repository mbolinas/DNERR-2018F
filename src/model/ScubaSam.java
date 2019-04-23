package model;

import controller.Controller;
import controller.ScubaSamController;

import java.awt.Dimension;
import java.io.Serializable;

/*
the player!
like most other GameObjects, it is manipulated via its own Controller
 */
public class ScubaSam extends GameObject implements Serializable {
	//Attributes
	private Net net;
	private ScubaSamController controller;
	///////////////////////////////////////////////////////////////////////////////
	//Constructor

	/**
	 * ScubaSame constructor
	 * @param controller
	 * @param location
	 */
	public ScubaSam(Controller controller, Dimension location){ //initial conditions
		super(controller, location, Type.DIVER, State.IDLE, new Dimension(216, 216), 16);
		this.controller = (ScubaSamController) controller;
		this.net = new Net(50);
	}
	///////////////////////////////////////////////////////////////////////////////
	//Methods

	/**
	 * Getter for net
	 * @return
	 */
	public Net getNet(){
		return net;
	}

	/**
	 * Getter for controller
	 * @return
	 */
	public ScubaSamController getController() {return controller;}
}