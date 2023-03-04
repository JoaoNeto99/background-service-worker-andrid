import {NativeModules} from 'react-native';

export function onStartBtnPress() {
  NativeModules.BackgroundWorkManager.startBackgroundWork();
}

export function onCancelBtnPress() {
  NativeModules.BackgroundWorkManager.stopBackgroundWork();
}
