package unitTestingExercise.p06_TirePressureMonitoringSystem;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

public class AlarmTest {

    private static final double LOW_PRESSURE_VALUE = 12.0;
    private static final double HIGH_PRESSURE_VALUE = 50.0;
    private static final double NORMAL_PRESSURE = 18.0;

    @Test
    public void testAlarmTurnsOnForLowPressure() {
        Sensor sensor = Mockito.mock(Sensor.class);
        when(sensor.popNextPressurePsiValue()).thenReturn(LOW_PRESSURE_VALUE);
        Alarm alarm = new Alarm(sensor);
        alarm.check();
        assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testAlarmTurnsOnForHighPressure() {
        Sensor sensor = Mockito.mock(Sensor.class);
        when(sensor.popNextPressurePsiValue()).thenReturn(HIGH_PRESSURE_VALUE);
        Alarm alarm = new Alarm(sensor);
        alarm.check();
        assertTrue(alarm.getAlarmOn());
    }

    @Test
    public void testAlarmIsOffForNormalPressure() {
        Sensor sensor = Mockito.mock(Sensor.class);
        when(sensor.popNextPressurePsiValue()).thenReturn(NORMAL_PRESSURE);
        Alarm alarm = new Alarm(sensor);
        alarm.check();
        assertFalse(alarm.getAlarmOn());
    }
}