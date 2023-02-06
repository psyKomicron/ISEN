package com.psy.rockfx.calculator;

/**
 * Record to store a resistance value with its ohm value and tolerance.
 * @param ohm Ohm
 * @param tolerance Tolerance
 */
public record ResistorValue(double ohm, float tolerance)
{
}
