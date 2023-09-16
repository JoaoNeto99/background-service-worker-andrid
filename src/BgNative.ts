import {NativeModules} from 'react-native';

export function onStartBtnPress() {
  NativeModules.BackgroundWorkManager.startBackgroundWork();
}

export function onCancelBtnPress() {
  NativeModules.BackgroundWorkManager.stopBackgroundWork();
}

export async function isRunning() {
  return await NativeModules.BackgroundWorkManager.isRunning();
}
