
package frc.robot;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.XboxController;

public class Robot extends TimedRobot {
  public SparkMax motorDerecho1;
  public SparkMax motorDerecho2;
  public SparkMaxConfig RightConfig1;
  public SparkMaxConfig RightConfig2;
  public SparkMax motorIzquierdo1;
  public SparkMax motorIzquierdo2;
  public SparkMaxConfig LeftConfig1;
  public SparkMaxConfig LeftConfig2;
  public XboxController control = new XboxController(Constants.kDriverControllerPort);
  //Lo que esta afuera es universal siempre existe
  //Variable tipo sparkmax
  public Robot() {//Esto se ejecuta al iniciar el robot
    //Iniciasion del  motor con función  ( motor = new SparkMax(numeroPuerto, MotorType.kBrushed);)
    //Donde puerto es el CAD ID y tipo de motor brushed(neo) o brushless(Sim))
    //Configuración de motores
    motorDerecho1 = new SparkMax(Constants.kRight1, MotorType.kBrushed);
    motorDerecho2 = new SparkMax(Constants.kRight2, MotorType.kBrushed);
    RightConfig1 = new SparkMaxConfig();
    RightConfig2 = new SparkMaxConfig();
    RightConfig1.inverted(true);
    RightConfig2.follow(motorDerecho1);
    motorDerecho1.configure(RightConfig1, null, null);
    motorDerecho2.configure(RightConfig2, null, null);
    motorIzquierdo1 = new SparkMax(Constants.kLeft1, MotorType.kBrushed);
    motorIzquierdo2 = new SparkMax(Constants.kLeft2, MotorType.kBrushed);
    LeftConfig1 = new SparkMaxConfig();
    LeftConfig2 = new SparkMaxConfig();
    LeftConfig1.inverted(false);
    LeftConfig2.follow(motorIzquierdo1);
    motorIzquierdo1.configure(LeftConfig1, null, null);
    motorIzquierdo2.configure(LeftConfig2, null, null);
  }
//Wpoilib Methods git hub commands git add "document"
  @Override
  public void robotPeriodic() {}

  @Override
  public void autonomousInit() {}

  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {}

  @Override
  public void teleopPeriodic() {

    drive(new ChassisSpeeds(
      -control.getLeftY()*Constants.kMeterSecondProportion, //SpeedX(Y) = -Y*kMeterProportion
      0.0,
      control.getRightX() * Constants.kRadProportion));
        // Botón A → Shooter
        if (control.getAButton()) {
          Shooter.shoot();
      } 
      else {
          Shooter.stopshoot();
      }
      
      if (control.getBButton()) {
          Shooter.shootbajo();
      } 
      else {
          Shooter.stopshootbajo();
      }
      // Botón Y → Samples
      if (control.getYButton()) {
          Shooter.sample();
      } 
      else {
          Shooter.stopsample();
      }
      if (control.getXButton()) {
        Shooter.samplebajo();
    } 
    else {
        Shooter.stopsamplebajo();
    }
  }

  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {}

  @Override
  public void testInit() {}

  @Override
  public void testPeriodic() {
    drive(new ChassisSpeeds(
      -control.getLeftY()*Constants.kMeterSecondProportion, //SpeedX(Y) = -Y*kMeterProportion
      0.0,
      control.getRightX() * Constants.kRadProportion));
    //drive();
  }
  // Escribir un metodo Drive que tome como parametros la velocidad V y velocidad angular W
  //Forma Pro de Chasis
  public void drive(ChassisSpeeds speeds){
    double V = speeds.vxMetersPerSecond;
    double W = speeds.omegaRadiansPerSecond/(2*Math.PI);
    double speedR = V+W;
    double speedL = V-W; 
    motorDerecho1.set(speedR);
    motorDerecho2.set(speedR);
    motorIzquierdo1.set(speedL); 
    motorIzquierdo1.set(speedL);
  }
  
}
