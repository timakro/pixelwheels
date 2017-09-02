package com.agateau.tinywheels;

/**
 * A pilot controlled by the player
 */
public class PlayerPilot implements Pilot {
    private final Assets mAssets;
    private final GameWorld mGameWorld;
    private final Racer mRacer;

    private GameInputHandler mInputHandler;

    public PlayerPilot(Assets assets, GameWorld gameWorld, Racer racer, GameInputHandler inputHandler) {
        mAssets = assets;
        mGameWorld = gameWorld;
        mRacer = racer;
        mInputHandler = inputHandler;
    }

    public void createHudButtons(Hud hud) {
        mInputHandler.createHudButtons(mAssets, hud);
    }

    @Override
    public void act(float dt) {
        Vehicle vehicle = mRacer.getVehicle();

        if (mGameWorld.getState() == GameWorld.State.RUNNING) {
            mInputHandler.setBonus(mRacer.getBonus());
            GameInput input = mInputHandler.getGameInput();
            vehicle.setDirection(input.direction);
            vehicle.setAccelerating(input.accelerating);
            vehicle.setBraking(input.braking);
            if (input.triggeringBonus) {
                mRacer.triggerBonus();
            }
        }
    }
}
