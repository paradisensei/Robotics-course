% plot robot
function [] = plot_robot(robot, varargin)
% parse optional input parameters
p = inputParser;
addParameter(p, 'q', [0 -pi/2 0 pi/2 0 0]);
addParameter(p, 'workspace', [-6.5 6.5 -6.5 6.5 -0.5 10]);
parse(p, varargin{:});

q = p.Results.q;
workspace = p.Results.workspace;

robot.plot(q, 'workspace', workspace);
robot.teach();

end