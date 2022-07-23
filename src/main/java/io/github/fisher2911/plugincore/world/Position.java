package io.github.fisher2911.plugincore.world;

import java.util.UUID;

public class Position {

    private final double x;
    private final double y;
    private final double z;

    protected Position(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public static Position of(double x, double y, double z) {
        return new Position(x, y, z);
    }

    public WorldPosition toWorldPosition(UUID world) {
        return WorldPosition.of(world, this.x, this.y, this.z);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public int getBlockX() {
        return (int) Math.floor(x);
    }

    public int getBlockY() {
        return (int) Math.floor(y);
    }

    public int getBlockZ() {
        return (int) Math.floor(z);
    }

    public Position add(double x, double y, double z) {
        return Position.of(this.x + x, this.y + y, this.z + z);
    }

    public Position add(Position position) {
        return Position.of(this.x + position.x, this.y + position.y, this.z + position.z);
    }

    public Position subtract(double x, double y, double z) {
        return Position.of(this.x - x, this.y - y, this.z - z);
    }

    public Position subtract(Position position) {
        return Position.of(this.x - position.x, this.y - position.y, this.z - position.z);
    }

    public Position multiply(double x, double y, double z) {
        return Position.of(this.x * x, this.y * y, this.z * z);
    }

    public Position multiply(Position position) {
        return Position.of(this.x * position.x, this.y * position.y, this.z * position.z);
    }

    public Position divide(double x, double y, double z) {
        return Position.of(this.x / x, this.y / y, this.z / z);
    }

    public Position divide(Position position) {
        return Position.of(this.x / position.x, this.y / position.y, this.z / position.z);
    }

    public Position floor() {
        return Position.of(Math.floor(x), Math.floor(y), Math.floor(z));
    }

    public Position ceil() {
        return Position.of(Math.ceil(x), Math.ceil(y), Math.ceil(z));
    }

    public Position round() {
        return Position.of(Math.round(x), Math.round(y), Math.round(z));
    }

    public Position abs() {
        return Position.of(Math.abs(x), Math.abs(y), Math.abs(z));
    }

    public Position max(double x, double y, double z) {
        return Position.of(Math.max(this.x, x), Math.max(this.y, y), Math.max(this.z, z));
    }

    public Position max(Position position) {
        return Position.of(Math.max(this.x, position.x), Math.max(this.y, position.y), Math.max(this.z, position.z));
    }

    public Position min(double x, double y, double z) {
        return Position.of(Math.min(this.x, x), Math.min(this.y, y), Math.min(this.z, z));
    }

    public Position min(Position position) {
        return Position.of(Math.min(this.x, position.x), Math.min(this.y, position.y), Math.min(this.z, position.z));
    }

}
