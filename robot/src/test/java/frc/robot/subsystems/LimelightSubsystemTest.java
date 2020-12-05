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
    @Disabled("Distance method is not used anymore")
    void distanceTest() {
        double distance;

        when(ty.getDouble(anyDouble())).thenReturn(13.62);
        distance = subsystem.getDistance();
        System.out.println(distance);
        assertTrue(distance > 125 && distance < 131); // ~3 inches is acceptible range

        when(ty.getDouble(anyDouble())).thenReturn(16.93);
        distance = subsystem.getDistance();
        assertTrue(distance > 110 && distance < 116);

        when(ty.getDouble(anyDouble())).thenReturn(21.25);
        distance = subsystem.getDistance();
        assertTrue(distance > 92 && distance < 98); 

        when(ty.getDouble(anyDouble())).thenReturn(10.82);
        distance = subsystem.getDistance();
        assertTrue(distance > 145 && distance < 151);

        when(ty.getDouble(anyDouble())).thenReturn(8.13);
        distance = subsystem.getDistance();
        assertTrue(distance > 161 && distance < 167);

        when(ty.getDouble(anyDouble())).thenReturn(6.42);
        distance = subsystem.getDistance();
        assertTrue(distance > 179 && distance < 185);
    }
}