package com.psy.rockfx.calculator;

public class Band
{
    private int rank;
    private BandColor color;

    public Band() {}

    public Band(BandColor color, int rank)
    {
        this.color = color;
        this.rank = rank;
    }

    public BandColor Color()
    {
        return color;
    }

    public void BandColor(BandColor value)
    {
        color = value;
    }

    public int Rank()
    {
        return rank;
    }

    public void Rank(int value)
    {
        rank = value;
    }
}
