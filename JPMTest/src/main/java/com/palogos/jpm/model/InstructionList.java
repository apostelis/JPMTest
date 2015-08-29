package com.palogos.jpm.model;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.UUID;

public class InstructionList<T> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private LinkedList<T> linkedAccess;
	private HashMap<String, T> fastAccess;
	private UUID uuid;

	public LinkedList<T> getLinkedAccess() {
		return linkedAccess;
	}

	public void setLinkedAccess(LinkedList<T> linkedAccess) {
		this.linkedAccess = linkedAccess;
	}

	public HashMap<String, T> getFastAccess() {
		return fastAccess;
	}

	public void setFastAccess(HashMap<String, T> fastAccess) {
		this.fastAccess = fastAccess;
	}

	public synchronized String addInstruction(T val) {
		String instructionId = UUID.randomUUID().toString();
		linkedAccess.add(val);
		fastAccess.put(instructionId, val);
		return instructionId;
	}
}
