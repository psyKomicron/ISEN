package com.psy.rockfx.calculator;

import java.util.ArrayList;

public class ResistorValueCalculator
{
    private ResistorValueCalculator()
    {
    }

    /**
     * Calculates the ohm value of a resistance, and it's tolerance.
     * @param bands Left most bands
     * @param multiplier Resistance multiplier
     * @param tolerance Resistance tolerance
     * @return ResistorValue contains the ohm value and tolerance.
     */
    public static ResistorValue Calculate(ArrayList<Band> bands, Band multiplier, Band tolerance)
    {
        long ohms = 0;
        for (var band : bands)
        {
            double rank = (bands.size() - 1) - band.Rank(); // Reverse the rank of the bands: index 0 (first band) is the largest power.
            ohms += GetColorValue(band.BandColor()) * (long)(Math.pow(10, rank));
        }

        float tol = switch (tolerance.BandColor())
                {
                    case Black, White, Yellow, Orange -> 0.0f;
                    case Brown -> 0.01f;
                    case Red -> 0.02f;
                    case Green -> 0.005f;
                    case Blue -> 0.0025f;
                    case Purple -> 0.001f;
                    case Gray -> 0.0005f;
                    case Gold -> 0.05f;
                    case Silver -> 0.1f;
                };

        return new ResistorValue(ohms * Math.pow(10, GetColorValue(multiplier.BandColor())), tol);
    }


    private static int GetColorValue(BandColor color)
    {
        switch (color)
        {
            case Black ->
            {
                return 0;
            }
            case Brown ->
            {
                return 1;
            }
            case Red ->
            {
                return 2;
            }
            case Orange ->
            {
                return 3;
            }
            case Yellow ->
            {
                return 4;
            }
            case Green ->
            {
                return 5;
            }
            case Blue ->
            {
                return 6;
            }
            case Purple ->
            {
                return 7;
            }
            case Gray ->
            {
                return 8;
            }
            case White ->
            {
                return 9;
            }
            case Gold ->
            {
                return -1;
            }
            case Silver ->
            {
                return -2;
            }
            default ->
            {
                throw new IllegalArgumentException();
            }
        }
    }
}

