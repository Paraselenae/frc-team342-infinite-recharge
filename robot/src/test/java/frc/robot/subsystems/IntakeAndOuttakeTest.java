package frc.robot.subsystems;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import edu.wpi.first.wpilibj.DigitalInput;

class IntakeAndOuttakeTest {
    static IntakeAndOutake subsystem;

    // sensors used to check for power cells
    static DigitalInput sensor1 = mock(DigitalInput.class);
    static DigitalInput sensor2 = mock(DigitalInput.class);
    static DigitalInput sensor3 = mock(DigitalInput.class);

    // limelight values are used for shooter
    private LimelightSubsystem limelight = mock(LimelightSubsystem.class);

    @BeforeAll
    static void init() {
        subsystem = new IntakeAndOutake();
        subsystem.setSensors(sensor1, sensor2, sensor3);
    }

    // Tests to make sure the power cell counter is correct
    @Test
    @Disabled("Not finished")
    void powerCellCounterTest() {
        // All sensors are empty
        when(sensor1.get()).thenReturn(true);
        when(sensor2.get()).thenReturn(true);
        when(sensor3.get()).thenReturn(true);
        assertEquals(0, subsystem.getPowerCellCount());

        when(sensor1.get()).thenReturn(false);
        assertEquals(1, subsystem.getPowerCellCount());

        subsystem.reverseIntake();
        assertEquals(0, subsystem.getPowerCellCount());
    }

    // Tests to make sure the intake stops when the hopper is full
    @Test
    @Disabled("Not finished")
    void fullHopperIntakeTest() {
        when(sensor3.get()).thenReturn(true);

        when(sensor3.get()).thenReturn(false);

    }

    @Test
    @Disabled("Not finished")
    void shooterSpeedTest() {
        // get limelight values that correspond to velocities
        when(limelight.getDistance()).thenReturn(0.0);
        assertEquals(0.0, 0.0);
    }
}