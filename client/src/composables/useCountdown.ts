import { Timer } from "@/types/TimerType";

const countdown = function(timer: Timer, callback: () => void): void {
  if (timer.minutes <= 0 && timer.seconds <= 0) {
    callback();
    return;
  }
  if (--timer.seconds < 0) {
    timer.seconds = 59;
    timer.minutes--;
  }
};

const countdownInterval = function(
  timer: Timer,
  callback: () => void
): ReturnType<typeof setInterval> {
  return setInterval(() => {
    countdown(timer, callback);
  }, 1000);
};

export { countdownInterval };
