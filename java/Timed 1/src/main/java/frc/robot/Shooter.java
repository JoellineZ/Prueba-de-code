package frc.robot;


import com.revrobotics.spark.SparkMax;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {
    private static SparkMax shooterMotor = new SparkMax(1, SparkMax.MotorType.kBrushless);
    private static SparkMax SampleMotor = new SparkMax(2, SparkMax.MotorType.kBrushless);
    
            // Métodos del subsistema Shooter
            // - Disparar
    public static void shoot(){
        shooterMotor.set(0.8); 
        }
        // - gatillo / Intake
    public static void sample(){
        SampleMotor.set(0.6); 
        }
    public static void stopshoot(){
        shooterMotor.set(0.0); // Detener shooter
    }
    public static void stopintake(){
        SampleMotor.set(0); // Detener intake
    }
}