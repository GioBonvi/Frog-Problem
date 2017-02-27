disp('Length => Solution')
for ii = 1:50
    v = ones(1, ii); 
    if checkPond([1 0 v 0 1], 1, false)
        result = 'Solution found!';
    else
        result = 'Solution NOT found!';
    end
   disp([num2str(ii + 4) ' => ' result]);
end
