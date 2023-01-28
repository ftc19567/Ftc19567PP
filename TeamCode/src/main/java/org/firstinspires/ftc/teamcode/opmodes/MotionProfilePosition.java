package org.firstinspires.ftc.teamcode.opmodes;

import com.qualcomm.robotcore.util.ElapsedTime;

public class MotionProfilePosition {
    ElapsedTime current = new ElapsedTime();
    public double motion_profile_position(double max_acceleration, double max_velocity, double distance) {
        //Return the current reference position based on the given motion profile times, maximum acceleration, velocity, and current time.
        double current_time = current.seconds();

        // calculate the time it takes to accelerate to max velocity
        double acceleration_time = max_velocity / max_acceleration;

        // If we can't accelerate to max velocity in the given distance, we'll accelerate as much as possible
        double halfway_distance = distance / 2;
        double acceleration_distance = 0.5 * max_acceleration * Math.pow(acceleration_time, 2);

        if (acceleration_distance > halfway_distance)
            acceleration_time = Math.sqrt(halfway_distance / (0.5 * max_acceleration));

        acceleration_distance = 0.5 * max_acceleration * Math.pow(acceleration_time, 2);

        // recalculate max velocity based on the time we have to accelerate and decelerate
        max_velocity = max_acceleration * acceleration_time;

        // we decelerate at the same rate as we accelerate
        double deacceleration_time = acceleration_time;

        // calculate the time that we're at max velocity
        double cruise_distance = distance - 2 * acceleration_distance;
        double cruise_time = cruise_distance / max_velocity;
        deacceleration_time = acceleration_time + cruise_time;

        // check if we're still in the motion profile
        double entire_dt = acceleration_time + cruise_time + deacceleration_time;
        if (current_time > entire_dt)
        {
            current.reset();
            return distance;
        }
        // if we're accelerating
        if (current_time < acceleration_time){
            // use the kinematic equation for acceleration
            return 0.5 * max_acceleration * Math.pow(current_time, 2);
        }
        // if we're cruising
        else if (current_time < deacceleration_time) {
            double cruise_current_time = current_time - acceleration_time;

            // use the kinematic equation for constant velocity
            return acceleration_distance + max_velocity * cruise_current_time;
        }
        // if we're decelerating
        else {
            deacceleration_time = current_time - deacceleration_time;

            // use the kinematic equations to calculate the instantaneous desired position
            return acceleration_distance + cruise_distance + max_velocity * deacceleration_time - 0.5 * max_acceleration * Math.pow(deacceleration_time, 2);
        }
    }
}
