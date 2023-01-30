package com.psy.rockfx.calculator;

import java.util.ArrayList;

public class ResistorValueCalculator
{
    public ResistorValueCalculator()
    {
    }

    public ResistorValue Calculate(ArrayList<Band> bands, Band multiplier, Band tolerance)
    {
        long ohms = 0;
        for (var band : bands)
        {
            ohms += GetColorValue(band.Color()) * (long)(Math.pow(10, band.Rank()));
        }

        return new ResistorValue(ohms * (long)Math.pow(10, GetColorValue(multiplier.Color())), 0);
    }


    private int GetColorValue(BandColor color)
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

