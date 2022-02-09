clear;
close all;

bitsCount = [8 12 16 20 24];
preimages = [256 4300 75241 1289194 22860714];
collisions = [2 4 12 44 183];

figure(1);
subplot(2, 1, 1);
plot(bitsCount, preimages, '-bo', 'MarkerFaceColor', 'b');
xlabel("Size, bits");
ylabel("Steps, n");
title("Second Preimages");
hold on;
grid on;

subplot(2, 1, 2);
plot(bitsCount, collisions, '-go', 'MarkerFaceColor', 'g');
xlabel("Size, bits");
ylabel("Steps, n");
title("Collisions");
hold on;
grid on;