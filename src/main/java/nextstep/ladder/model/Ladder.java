package nextstep.ladder.model;

public class Ladder {
    private final Legs legs;
    private final Names names;

    public Ladder(Names names, CoordinateValue height, int legCount) {
        this.legs = new Legs(legCount, height);
        this.names = names;
    }

    public CoordinateValue getHeight() {
        return legs.getHeight();
    }

    public CoordinateValue getWidth() {
        return new CoordinateValue(legs.size());
    }

    public Names getNames() {
        return names;
    }

    public void drawLines(LineDrawStrategy strategy) {
        for (CoordinateValue widthIndex = CoordinateValue.ZERO; widthIndex.smallerThan(new CoordinateValue(legs.size() - 1)); widthIndex = widthIndex.increment()) {
            drawLinesBetweenLegs(legs.get(widthIndex), legs.get(widthIndex.increment()), strategy);
        }
    }

    private void drawLinesBetweenLegs(Leg leftLeg, Leg rightLeg, LineDrawStrategy strategy) {
        for (CoordinateValue heightIndex = CoordinateValue.ZERO; heightIndex.smallerThan(getHeight()); heightIndex = heightIndex.increment()) {
            drawLineByStrategy(strategy, leftLeg, rightLeg, heightIndex);
        }
    }

    private void drawLineByStrategy(LineDrawStrategy strategy, Leg leftLeg, Leg rightLeg, CoordinateValue heightIndex) {
        if (strategy.decideToDraw() && !leftLeg.hasLine(heightIndex) && !rightLeg.hasLine(heightIndex)) {
            Line line = new Line(heightIndex);
            leftLeg.register(line);
            rightLeg.register(line);
        }
    }

    public Leg getLeg(CoordinateValue coordinateValue) {
        return legs.get(coordinateValue);
    }
}
