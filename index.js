/**
 * @format
 */

import {AppRegistry} from 'react-native';
import App from './App';
import {name as appName} from './app.json';
import { BackgroundHeadlessTask } from "./src/BackgroundHeadlessTask";

AppRegistry.registerComponent(appName, () => App);
AppRegistry.registerHeadlessTask(
  'BackgroundHeadlessTaskService',
  () => BackgroundHeadlessTask,
);
