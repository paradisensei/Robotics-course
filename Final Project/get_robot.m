% design robot
function [robot] = get_robot

L1 = Revolute('alpha', -pi/2);
L2 = Revolute('a', 2.5);
L3 = Revolute('a', 2.5);
L4 = Revolute('alpha', -pi/2, 'd', -1);
L5 = Revolute('alpha', pi/2, 'd', -1);
L6 = Revolute('alpha', pi/2, 'd', -2);

robot = SerialLink([L1 L2 L3 L4 L5 L6]);

end