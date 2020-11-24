package frc.robot.subsystems;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;

class LimelightSubsystemTest {
    static LimelightSubsystem subsystem;

    static NetworkTable table = mock(NetworkTable.class);
    static NetworkTableEntry ty = mock(NetworkTableEntry.class);

    @BeforeAll
    static void init() {
        subsystem = new LimelightSubsystem();
        when(table.getEntry("ty")).thenReturn(ty);
        
        subsystem.setNetworkTable(table);
    }

    @Test
    void distanceTest() {
        double distance;

        // TODO: get limelight values that correspond to distances
        when(ty.getDouble(anyDouble())).thenReturn(0.0);
        distance = subsystem.getDistance();
        assertTrue(distance > -3 && distance < 3); // ~3 inches is acceptible range

        when(ty.getDouble(anyDouble())).thenReturn(0.0);
        distance = subsystem.getDistance();
        assertTrue(distance > -3 && distance < 3);

        when(ty.getDouble(anyDouble())).thenReturn(0.0);
        distance = subsystem.getDistance();
        assertTrue(distance > -3 && distance < 3);
    }
}