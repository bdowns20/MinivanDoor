package edu.vt.cs5044;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * Purpose of this program is to monitor and control the operation of the power sliding door of a
 * minivan.
 *
 * @author barrettdowns
 * @version Feb 24, 2020
 *
 */
public class MinivanDoor
{

    private final List<LogEntry> eventLog;
    private Gear gear;
    private boolean locked;
    private boolean open;
    private boolean childsafe;

    /**
     * Create a new MinivanDoor object.
     *
     */
    public MinivanDoor()
    {
        eventLog = new ArrayList<LogEntry>();
        gear = Gear.PARK;
        locked = false;
        open = false;
        childsafe = false;
    }

    /**
     * Open door 
     *
     * @return Open 
     */
    public boolean isOpen()
    {
        return open;
    }

    /**
     * Is locked 
     *
     * @return locked 
     */
    public boolean isLocked()
    {
        return locked;
    }

    /**
     * this methold returns child is safe 
     *
     * @return childsafe 
     */
    public boolean isChildSafe()
    {
        return childsafe;
    }

    /**
     * this method gets the gear and has dependency with 3 other mutators 
     *
     * @return the gear 
     */
    public Gear getGear()
    {
        return this.gear;
    }

    /**
     * Returns last log entry 
     *
     * @return last log entry 
     */
    public LogEntry getLastLogEntry()
    {
        int longEntryCount = getLogEntryCount();
        if (longEntryCount > 0)
        {

            return getLogEntryAt(longEntryCount - 1);

        }

        return null;

    }

    /**
     * returns entry 
     *
     * @return count into the event log 
     */
    public int getLogEntryCount()
    {
        return eventLog.size();
    }

    /**
     * Log entry method 
     *
     * @param index last element is at index 
     * @return log entry at index 
     */
    public LogEntry getLogEntryAt(int index)
    {
        return eventLog.get(index);
    }

    /**
     * Gear used to determine what action to take based on gear position 
     *
     * @param newGear used for enumeration 
     */
    public void setGear(Gear newGear)
    {
        if (newGear == this.gear)
        {
            eventLog.add(LogEntry.NO_ACTION_TAKEN);
        }
        else if (newGear != Gear.PARK)
        {
            eventLog.add(LogEntry.GEAR_RELEASED);
        }
        else
        {
            eventLog.add(LogEntry.GEAR_PARKED);
        }
        this.gear = newGear;
    }

    /**
     * Used to action needed in sequence of events 
     *
     * @param engage child safe feature 
     */
    public void setChildSafe(boolean engage)
    {
        if (!isOpen())
        {
            eventLog.add(LogEntry.CHILD_SAFE_CHANGE_REFUSED);
            return;
        }
        if (engage == this.childsafe)
        {
            eventLog.add(LogEntry.NO_ACTION_TAKEN);
        }
        else if (!this.childsafe)
        {
            eventLog.add(LogEntry.CHILD_SAFE_ENGAGED);
        }
        else
        {
            eventLog.add(LogEntry.CHILD_SAFE_DISENGAGED);
        }
        this.childsafe = engage; // set our child-safely to the new value
    }

    /**
     * Lock button method used to either take no action or lock doors 
     *
     */
    public void pushLockButton()
    {
        if (locked)
        {
            // locked
            eventLog.add(LogEntry.NO_ACTION_TAKEN);
        }
        else
        {
            // unlocked
            locked = true;
            eventLog.add(LogEntry.DOOR_LOCKED);

        }

    }

    /**
     * Unlock button used to either unlock doors or take no action 
     *
     */
    public void pushUnlockButton()
    {
        if (this.locked)
        {
            this.locked = false;
            eventLog.add(LogEntry.DOOR_UNLOCKED);
        }
        else
        {
            eventLog.add(LogEntry.NO_ACTION_TAKEN);
        }
    }

    /**
     * Simple method used to open 
     *
     */
    public void pushOpenButton()
    {
        processBasicOpen();
    }

    /**
     * Simple method used to pull inside handle 
     *
     */
    public void pullInsideHandle()
    {

        if (isChildSafe())
        {
            eventLog.add(LogEntry.OPEN_REFUSED_CHILD_SAFE);
        }
        else
        {
            processBasicOpen();
        }
    }

    /**
     * Simple method used to pull inside handle 
     *
     */
    public void pullOutsideHandle()
    {
        processBasicOpen();
    }

    /**
     * Used to reduce redundancy with push open & pull outside handle 
     *
     */
    private void processBasicOpen()
    {
        if (isOpen())
        {

            eventLog.add(LogEntry.NO_ACTION_TAKEN);

        }
        else if (getGear() != Gear.PARK)
        {

            eventLog.add(LogEntry.OPEN_REFUSED_GEAR);

        }
        else if (isLocked())
        {

            eventLog.add(LogEntry.OPEN_REFUSED_LOCK);

        }
        else
        {

            this.open = true;

            eventLog.add(LogEntry.DOOR_OPENED);

        }
    }

    /**
     * Used to close the door.
     *
     */
    public void closeDoor()
    {
        if (this.open)
        {
            this.open = false;
            eventLog.add(LogEntry.DOOR_CLOSED);
        }
        else
        {
            eventLog.add(LogEntry.NO_ACTION_TAKEN);
        }
    }

}
