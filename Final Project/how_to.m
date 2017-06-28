% HOWTO function
function [q] = how_to(t_matrix)
warning('off','all');

% validate position vector
posX = t_matrix(1,4);
posY = t_matrix(2,4);
posZ = t_matrix(3,4);

msg = '%s coordinate in position vector must be in [%d; %d]';

if posX > 6 || posX < -6
    throw(MException('how_to:posX', msg, 'X', -6, 6));
end

if posY > 6 || posY < -6
    throw(MException('how_to:posY', msg, 'Y', -6, 6));
end

if posZ < 0 || posZ > 6
    throw(MException('how_to:posZ', msg, 'Z', 0, 6));
end

% limit of iterations.
L = 3000;

% step size
ALPHA = 0.2;

robot = get_robot;

% normalize transformation matrix to make it orthogonal.
[U, ~, V]=svd(t_matrix(1:3, 1:3));

t_corrected = U*V';
t_matrix = [t_corrected, t_matrix(1:3, end); 0 0 0 1];

q = robot.ikine(t_matrix, 'ilimit', L, 'alpha', ALPHA);

plot_robot(robot, 'q', q);

end