package edu.vt.cs5044;

import static org.junit.Assert.*; 

import org.junit.Before;
import org.junit.Test;

//CHECKSTYLE:OFF
/**
 * A starter JUnit test class for the MinivanDoor.
 *
 * NOTE: Javadocs are NOT required in this file. Comments are provided here to help document the
 * example test cases.
 *
 * Please be sure name all of your test methods very clearly, with very descriptive method names,
 * using in-line comments as necessary. Also be sure to make it clear what single "action" is in
 * each method.
 *
 * NOTE: Web-CAT will completely ignore style rules on this file. Leave the below
 * "@SuppressWarnings" annotation intact, to avoid Eclipse warnings about Javadocs, and keep the
 * CHECKSTYLE:OFF comment above.
 *
 * @version Spring 2020 (Starter code only)
 *
 */

@SuppressWarnings("javadoc")
public class MinivanDoorTest
{

    private MinivanDoor vanDoor;

    /*
     * Called by JUnit each time it calls any test method. Note the "@Before" annotation,
     * immediately above the method signature. You'll need to use the "@Test" annotation for all
     * your test methods. We'll use this to simply create a new default instance of MinivanDoor.
     */
    @Before
    public void setUp()
    {
        vanDoor = new MinivanDoor();
    }

    /*
     * Tests all the initial values of the constructor. This also requires most accessor methods to
     * be implemented properly.
     *
     * Don't forget the "@Test" annotation.
     */
    @Test
    public void testConstructorDefaults()
    {
        // Default setup

        assertFalse(vanDoor.isLocked());
        assertFalse(vanDoor.isOpen());
        assertFalse(vanDoor.isChildSafe());
        assertEquals(Gear.PARK, vanDoor.getGear());
        assertEquals(0, vanDoor.getLogEntryCount());
        assertNull(vanDoor.getLastLogEntry());
    }

    /*
     * Test activation of the lock button with default setup. We expect the door to lock
     * successfully, with an event log entry that reflects this.
     *
     * Don't forget the "@Test" annotation.
     */
    @Test
    public void testLockDoorWithDefaults()
    {
        testConstructorDefaults();

        vanDoor.pushLockButton(); // ACTION

        assertTrue(vanDoor.isLocked());
        assertEquals(LogEntry.DOOR_LOCKED, vanDoor.getLastLogEntry());
    }

    @Test
    public void testLockDoorAlreadyLocked()
    {
        testLockDoorWithDefaults();

        vanDoor.pushLockButton(); // ACTION

        assertTrue(vanDoor.isLocked());
        assertEquals(LogEntry.NO_ACTION_TAKEN, vanDoor.getLastLogEntry());
    }

    @Test
    public void testunLockDoorWithDefaults()
    {
        testConstructorDefaults();

        vanDoor.pushUnlockButton(); // ACTION

        assertFalse(vanDoor.isLocked());
        assertEquals(LogEntry.NO_ACTION_TAKEN, vanDoor.getLastLogEntry());
    }

    @Test
    public void testunlockDoorFromLocked()
    {
        testLockDoorWithDefaults();

        vanDoor.pushUnlockButton(); // ACTION

        assertFalse(vanDoor.isLocked());
        assertEquals(LogEntry.DOOR_UNLOCKED, vanDoor.getLastLogEntry());
    }

    @Test
    public void testLockDoorFromUnlocked()
    {
        testLockDoorWithDefaults();

        vanDoor.pushUnlockButton(); // ACTION
        vanDoor.pushLockButton(); // ACTION

        assertTrue(vanDoor.isLocked());
        assertEquals(LogEntry.DOOR_LOCKED, vanDoor.getLastLogEntry());
    }

    @Test
    public void testGearParkToPark()
    {
        testConstructorDefaults();

        vanDoor.setGear(Gear.PARK);

        assertEquals(Gear.PARK, vanDoor.getGear());
        assertEquals(LogEntry.NO_ACTION_TAKEN, vanDoor.getLastLogEntry());

    }

    @Test
    public void testGearParkToNeutral()
    {
        testConstructorDefaults();

        vanDoor.setGear(Gear.NEUTRAL);

        assertEquals(Gear.NEUTRAL, vanDoor.getGear());
        assertEquals(LogEntry.GEAR_RELEASED, vanDoor.getLastLogEntry());

    }

    @Test
    public void testGearNeutralToPark()
    {
        testGearParkToNeutral();

        vanDoor.setGear(Gear.PARK);

        assertEquals(Gear.PARK, vanDoor.getGear());
        assertEquals(LogEntry.GEAR_PARKED, vanDoor.getLastLogEntry());

    }

    @Test
    public void testGearNeutralToDrive()
    {
        testGearParkToNeutral();

        vanDoor.setGear(Gear.DRIVE);

        assertEquals(Gear.DRIVE, vanDoor.getGear());
        assertEquals(LogEntry.GEAR_RELEASED, vanDoor.getLastLogEntry());

    }

    @Test
    public void testGearNeutralToReverse()
    {
        testGearParkToNeutral();

        vanDoor.setGear(Gear.REVERSE);

        assertEquals(Gear.REVERSE, vanDoor.getGear());
        assertEquals(LogEntry.GEAR_RELEASED, vanDoor.getLastLogEntry());

    }

    @Test
    public void testGearNeutralToNeutral()
    {
        testGearParkToNeutral();

        vanDoor.setGear(Gear.NEUTRAL);

        assertEquals(Gear.NEUTRAL, vanDoor.getGear());
        assertEquals(LogEntry.NO_ACTION_TAKEN, vanDoor.getLastLogEntry());

    }

    @Test
    public void testGearParkedToReverse()
    {
        testGearParkToPark();

        vanDoor.setGear(Gear.REVERSE);

        assertEquals(Gear.REVERSE, vanDoor.getGear());
        assertEquals(LogEntry.GEAR_RELEASED, vanDoor.getLastLogEntry());

    }

    @Test
    public void testGearParkedToDrive()
    {
        testGearParkToPark();

        vanDoor.setGear(Gear.DRIVE);

        assertEquals(Gear.DRIVE, vanDoor.getGear());
        assertEquals(LogEntry.GEAR_RELEASED, vanDoor.getLastLogEntry());

    }

    @Test
    public void testGearReverseToParked()
    {
        testGearParkedToReverse();

        vanDoor.setGear(Gear.PARK);

        assertEquals(Gear.PARK, vanDoor.getGear());
        assertEquals(LogEntry.GEAR_PARKED, vanDoor.getLastLogEntry());

    }

    @Test
    public void testGearReverseToReverse()
    {
        testGearParkedToReverse();

        vanDoor.setGear(Gear.REVERSE);

        assertEquals(Gear.REVERSE, vanDoor.getGear());
        assertEquals(LogEntry.NO_ACTION_TAKEN, vanDoor.getLastLogEntry());

    }

    @Test
    public void testGearReverseToNeutral()
    {
        testGearParkedToReverse();

        vanDoor.setGear(Gear.NEUTRAL);

        assertEquals(Gear.NEUTRAL, vanDoor.getGear());
        assertEquals(LogEntry.GEAR_RELEASED, vanDoor.getLastLogEntry());

    }

    @Test
    public void testGearReverseToDrive()
    {
        testGearParkedToReverse();

        vanDoor.setGear(Gear.DRIVE);

        assertEquals(Gear.DRIVE, vanDoor.getGear());
        assertEquals(LogEntry.GEAR_RELEASED, vanDoor.getLastLogEntry());

    }

    @Test
    public void testGearDriveToParked()
    {
        testGearParkedToDrive();

        vanDoor.setGear(Gear.PARK);

        assertEquals(Gear.PARK, vanDoor.getGear());
        assertEquals(LogEntry.GEAR_PARKED, vanDoor.getLastLogEntry());

    }

    @Test
    public void testGearDriveToReverse()
    {
        testGearParkedToDrive();

        vanDoor.setGear(Gear.REVERSE);

        assertEquals(Gear.REVERSE, vanDoor.getGear());
        assertEquals(LogEntry.GEAR_RELEASED, vanDoor.getLastLogEntry());

    }

    @Test
    public void testGearDriveToNeutral()
    {
        testGearParkedToDrive();

        vanDoor.setGear(Gear.NEUTRAL);

        assertEquals(Gear.NEUTRAL, vanDoor.getGear());
        assertEquals(LogEntry.GEAR_RELEASED, vanDoor.getLastLogEntry());

    }

    @Test
    public void testGearDriveToDrive()
    {
        testGearParkedToDrive();

        vanDoor.setGear(Gear.DRIVE);

        assertEquals(Gear.DRIVE, vanDoor.getGear());
        assertEquals(LogEntry.NO_ACTION_TAKEN, vanDoor.getLastLogEntry());

    }

    /*
     * Test opening the door with the open button, after locking the door. We expect the door to
     * remain closed and locked in this case, with an event log entry that reflects this.
     *
     * Note how we use another test case as the setup here, so we can be sure that the setup itself
     * actually worked as expected before continuing.
     *
     * Don't forget the "@Test" annotation.
     */
    @Test
    public void testOpenButtonAfterLockedWithDefaults()
    {
        testLockDoorWithDefaults();

        vanDoor.pushOpenButton(); // ACTION

        assertTrue(vanDoor.isLocked());
        assertFalse(vanDoor.isOpen());
        assertEquals(LogEntry.OPEN_REFUSED_LOCK, vanDoor.getLastLogEntry());
    }

    @Test
    public void testOpenButtonWithDefaults()
    {
        vanDoor.pushOpenButton(); // ACTION

        assertTrue(vanDoor.isOpen());
        assertEquals(LogEntry.DOOR_OPENED, vanDoor.getLastLogEntry());
    }

    @Test
    public void testOpenButtonAfterOpen()
    {
        vanDoor.pushOpenButton(); // ACTION
        vanDoor.pushOpenButton(); // ACTION

        assertTrue(vanDoor.isOpen());
        assertEquals(LogEntry.NO_ACTION_TAKEN, vanDoor.getLastLogEntry());
    }

    @Test
    public void testOpenButtonAfterGearToDrive()
    {
        vanDoor.setGear(Gear.DRIVE);

        vanDoor.pushOpenButton(); // ACTION

        assertFalse(vanDoor.isOpen());
        assertEquals(LogEntry.OPEN_REFUSED_GEAR, vanDoor.getLastLogEntry());
    }

    @Test
    public void testOpenButtonAfterGearToNeutral()
    {
        vanDoor.setGear(Gear.NEUTRAL);

        vanDoor.pushOpenButton(); // ACTION

        assertFalse(vanDoor.isOpen());
        assertEquals(LogEntry.OPEN_REFUSED_GEAR, vanDoor.getLastLogEntry());
    }

    @Test
    public void testOpenButtonAfterGearToReverse()
    {
        vanDoor.setGear(Gear.REVERSE);

        vanDoor.pushOpenButton(); // ACTION

        assertFalse(vanDoor.isOpen());
        assertEquals(LogEntry.OPEN_REFUSED_GEAR, vanDoor.getLastLogEntry());
    }

    @Test
    public void testPullInsideHandle()

    {
        testLockDoorWithDefaults();

        vanDoor.pullInsideHandle(); // ACTION

        assertTrue(vanDoor.isLocked());
        assertFalse(vanDoor.isOpen());
        assertEquals(LogEntry.OPEN_REFUSED_LOCK, vanDoor.getLastLogEntry());

        vanDoor.pushUnlockButton();

        assertFalse(vanDoor.isLocked());

        vanDoor.pullInsideHandle(); // ACTION

        assertTrue(vanDoor.isOpen());
        assertEquals(LogEntry.DOOR_OPENED, vanDoor.getLastLogEntry());

    }

    @Test
    public void testPullInsideHandleAfterLockedWithDefaults()
    {
        testLockDoorWithDefaults();

        vanDoor.pullInsideHandle(); // ACTION

        assertTrue(vanDoor.isLocked());
        assertFalse(vanDoor.isOpen());
        assertEquals(LogEntry.OPEN_REFUSED_LOCK, vanDoor.getLastLogEntry());
    }

    @Test
    public void testPullInsideHandleWithDefaults()
    {
        vanDoor.pullInsideHandle(); // ACTION

        assertTrue(vanDoor.isOpen());
        assertEquals(LogEntry.DOOR_OPENED, vanDoor.getLastLogEntry());
    }

    @Test
    public void testPullInsideHandleAfterOpen()
    {
        vanDoor.pullInsideHandle(); // ACTION
        vanDoor.pullInsideHandle(); // ACTION

        assertTrue(vanDoor.isOpen());
        assertEquals(LogEntry.NO_ACTION_TAKEN, vanDoor.getLastLogEntry());
    }

    @Test
    public void testPullInsideHandleWhenEngaged()
    {
        vanDoor.pushOpenButton();
        vanDoor.setChildSafe(true);
        vanDoor.closeDoor();

        vanDoor.pullInsideHandle(); // ACTION

        assertFalse(vanDoor.isOpen());
        assertEquals(LogEntry.OPEN_REFUSED_CHILD_SAFE, vanDoor.getLastLogEntry());
    }

    @Test
    public void testPullInsideHandleAfterGearToDrive()
    {
        vanDoor.setGear(Gear.DRIVE);

        vanDoor.pullInsideHandle(); // ACTION

        assertFalse(vanDoor.isOpen());
        assertEquals(LogEntry.OPEN_REFUSED_GEAR, vanDoor.getLastLogEntry());
    }

    @Test
    public void testPullInsideHandleAfterGearToNeutral()
    {
        vanDoor.setGear(Gear.NEUTRAL);

        vanDoor.pullInsideHandle(); // ACTION

        assertFalse(vanDoor.isOpen());
        assertEquals(LogEntry.OPEN_REFUSED_GEAR, vanDoor.getLastLogEntry());
    }

    @Test
    public void testPullInsideHandleAfterGearToReverse()
    {
        vanDoor.setGear(Gear.REVERSE);

        vanDoor.pullInsideHandle(); // ACTION

        assertFalse(vanDoor.isOpen());
        assertEquals(LogEntry.OPEN_REFUSED_GEAR, vanDoor.getLastLogEntry());
    }

    @Test
    public void testPullOutsideHandle()

    {

        testLockDoorWithDefaults();

        vanDoor.pullOutsideHandle(); // ACTION

        assertTrue(vanDoor.isLocked());

        assertFalse(vanDoor.isOpen());

        assertEquals(LogEntry.OPEN_REFUSED_LOCK, vanDoor.getLastLogEntry());

        vanDoor.pushUnlockButton();

        assertFalse(vanDoor.isLocked());

        vanDoor.pullOutsideHandle(); // ACTION

        assertTrue(vanDoor.isOpen());

        assertEquals(LogEntry.DOOR_OPENED, vanDoor.getLastLogEntry());

    }

    @Test
    public void testPullOutsideHandleAfterLockedWithDefaults()
    {
        testLockDoorWithDefaults();

        vanDoor.pullOutsideHandle(); // ACTION

        assertTrue(vanDoor.isLocked());
        assertFalse(vanDoor.isOpen());
        assertEquals(LogEntry.OPEN_REFUSED_LOCK, vanDoor.getLastLogEntry());
    }

    @Test
    public void testPullOutsideHandleWithDefaults()
    {
        vanDoor.pullOutsideHandle(); // ACTION

        assertTrue(vanDoor.isOpen());
        assertEquals(LogEntry.DOOR_OPENED, vanDoor.getLastLogEntry());
    }

    @Test
    public void testPullOutsideHandleAfterOpen()
    {
        vanDoor.pullOutsideHandle(); // ACTION
        vanDoor.pullOutsideHandle(); // ACTION

        assertTrue(vanDoor.isOpen());
        assertEquals(LogEntry.NO_ACTION_TAKEN, vanDoor.getLastLogEntry());
    }

    @Test
    public void testPullOutsideHandleAfterGearToDrive()
    {
        vanDoor.setGear(Gear.DRIVE);

        vanDoor.pullOutsideHandle(); // ACTION

        assertFalse(vanDoor.isOpen());
        assertEquals(LogEntry.OPEN_REFUSED_GEAR, vanDoor.getLastLogEntry());
    }

    @Test
    public void testPullOutsideHandleAfterGearToNeutral()
    {
        vanDoor.setGear(Gear.NEUTRAL);

        vanDoor.pullOutsideHandle(); // ACTION

        assertFalse(vanDoor.isOpen());
        assertEquals(LogEntry.OPEN_REFUSED_GEAR, vanDoor.getLastLogEntry());
    }

    @Test
    public void testPullOutsideHandleAfterGearToReverse()
    {
        vanDoor.setGear(Gear.REVERSE);

        vanDoor.pullOutsideHandle(); // ACTION

        assertFalse(vanDoor.isOpen());
        assertEquals(LogEntry.OPEN_REFUSED_GEAR, vanDoor.getLastLogEntry());
    }

    @Test
    public void testEngageChildSafetyWithDefaults() {
        vanDoor.setChildSafe(true);

        assertFalse(vanDoor.isChildSafe());

        assertEquals(LogEntry.CHILD_SAFE_CHANGE_REFUSED, vanDoor.getLastLogEntry());
    }

    @Test
    public void testDisengageChildSafetyWithDefaults() {
        vanDoor.setChildSafe(false);

        assertFalse(vanDoor.isChildSafe());

        assertEquals(LogEntry.CHILD_SAFE_CHANGE_REFUSED, vanDoor.getLastLogEntry());
    }

    @Test
    public void testEngageChildSafetyWithOpenDoor() {
        testOpenButtonWithDefaults();
        vanDoor.setChildSafe(true);

        assertTrue(vanDoor.isChildSafe());

        assertEquals(LogEntry.CHILD_SAFE_ENGAGED, vanDoor.getLastLogEntry());
    }

    @Test
    public void testDisengageChildSafetyWhenEngaged() {
        testOpenButtonWithDefaults();
        vanDoor.setChildSafe(true);
        vanDoor.setChildSafe(false);

        assertFalse(vanDoor.isChildSafe());

        assertEquals(LogEntry.CHILD_SAFE_DISENGAGED, vanDoor.getLastLogEntry());
    }

    @Test
    public void testEngageChildSafetyWhenEngaged() {
        testEngageChildSafetyWithOpenDoor();
        vanDoor.setChildSafe(true);

        assertTrue(vanDoor.isChildSafe());

        assertEquals(LogEntry.NO_ACTION_TAKEN, vanDoor.getLastLogEntry());
    }

    @Test
    public void testDisengageChildSafetyWhenDisengaged() {
        testDisengageChildSafetyWhenEngaged();
        vanDoor.setChildSafe(false);

        assertFalse(vanDoor.isChildSafe());

        assertEquals(LogEntry.NO_ACTION_TAKEN, vanDoor.getLastLogEntry());
    }

    @Test
    public void testCloseDoorWithDefaults() {
        vanDoor.closeDoor();

        assertFalse(vanDoor.isOpen());

        assertEquals(LogEntry.NO_ACTION_TAKEN, vanDoor.getLastLogEntry());
    }

    @Test
    public void testCloseDoorFromOpen() {
        testOpenButtonWithDefaults();
        vanDoor.closeDoor();

        assertFalse(vanDoor.isOpen());

        assertEquals(LogEntry.DOOR_CLOSED, vanDoor.getLastLogEntry());
    }
}
