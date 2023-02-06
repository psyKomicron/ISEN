package com.psy.rockfx.calculator;

/**
 * Represents a "band" on a resistance. A band is composed of a color and it's position/rank on the resistance.
 * Leftmost band has the rank 0.
 */
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

    /**
     * Gets the color of the band. See BandColor enum to see possible values.
     * @return BandColor enum
     */
    public BandColor BandColor()
    {
        return color;
    }

    /**
     * Sets the color of the band.
     * @param value The new color
     */
    public void BandColor(BandColor value)
    {
        color = value;
    }

    /**
     * Gets the rank/position (0 based index) of the band. Leftmost band has the rank 0.
     * @return
     */
    public int Rank()
    {
        return rank;
    }

    /**
     * Sets the rank/position of the band.
     * @param value The new rank.
     */
    public void Rank(int value)
    {
        rank = value;
    }
}
