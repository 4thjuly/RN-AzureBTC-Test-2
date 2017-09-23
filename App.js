import React from 'react';
import { StyleSheet, Text, View, Button } from 'react-native';
import { StackNavigator } from 'react-navigation';
import MainScreen from './MainScreen';
import ScanScreen from './ScanScreen';

export default SimpleApp = StackNavigator({
  Main: { screen: MainScreen },
  Scan: { screen: ScanScreen }
});
