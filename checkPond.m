function [solutionFound] = checkPond(arr, start, showMoves)
% checkPond - Recursive function which solves "The frog problem" by brute force.
% See https://www.youtube.com/watch?v=X3HDnrehyDM&t=0s for the full problem.
% Giorgio Bonvicini - 24/02/2017
% Parameters:
%   arr: an array consisting of ones (the frogs) and zeros (the lilypads they
%       cannot use);
%   start: index from which to start the search. See the comment at the end of
%       the move function to see how this is calculated;
%   showMoves: a boolean, if true the program prints the list of the moves, else
%       it only states if a solution exists or not;
% Returns:
%   solutionFound: boolean that indicates wether the pond has a solution or not.

    solutionFound = false;

    if sum(arr ~= 0) == 1
        % If all the frogs are on one lilypad the problem has a solution.
        solutionFound = true;
    end

    ii = 1;
    notZeroIndexes = find(arr(start:end) ~= 0) + start - 1;
    while ii <= length(notZeroIndexes) && solutionFound == false
        % Try to move each pile of frogs to the right.
        [newArr, fia] = movePile(arr, notZeroIndexes(ii), +1);
        if ~isempty(newArr)
            solutionFound = checkPond(newArr, fia, showMoves);
        end
        % Try to move each pile of frogs to the left.
        [newArr, fia] = movePile(arr, notZeroIndexes(ii), -1);
        if ~isempty(newArr)
            solutionFound = solutionFound || checkPond(newArr, fia, showMoves);
        end
        ii = ii + 1;
    end
    
    % If a solution exists and the user requests it, then display the entire moveset.
    if solutionFound && showMoves
        disp(arr);
    end
end

function [arr, lowestIndexAffected] = movePile(arr, pos, direction)
% movePile - Move a pile of frogs.
% Giorgio Bonvicini - 24/02/2017
% Parameters:
%   arr: the array representing frogs and lilypads;
%   pos: the index of the pile that should be moved;
%   direction: +1 => right, -1=> left;
% Returns:
%   arr: the array representing frogs and lilypads;
%   lowestIndexAffected: the index with the lovest value that was affected by the
%   move.

    % Find the destination of the pile.
    newPos = pos + direction * arr(pos);
    
    % These are invalid moves.
    if newPos > length(arr) || newPos < 1 || arr(newPos) == 0
        arr = [];
        lowestIndexAffected = 0;
        return;
    end
    
    % Copy the pile to the destination.
    arr(newPos) = arr(newPos) + arr(pos);
    % Remove the pile from the origin.
    arr(pos) = 0;
    
    % There is no need to check indexes lower than lowestIndexAffected.
    if direction == +1
        % If the pile was moved to the right the lowest index to check is the
        % one at the right of the original position.
        lowestIndexAffected = pos + 1;
    else
        % If the pile was moved to the left the lowest index to check is the one
        % where the pile was moved.
        lowestIndexAffected = newPos;
    end
end
