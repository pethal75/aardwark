package com.paragon.controller.rules;

import com.paragon.controller.ParagonException;
import com.paragon.model.Paragon;

public interface ParagonRule {

	public void processParagon(Paragon paragon) throws ParagonException;
}
