// @flow
import React from 'react';
import { StyleSheet, Text, View, Button, Image } from 'react-native';
import * as ImagePicker from 'react-native-image-picker';
// import ImageResizer from 'react-native-image-resizer';

export default class ResizeImageScreen extends React.Component {
    static navigationOptions = { title: 'Image Resize' };
    state = { imageSrc: null, width: 0, height:0 }

    render() {
        return (
            <View style={styles.container}>
            <View style={styles.top} >
              <Button style={{margin:5}} title=" Load " onPress={this.load} />    
              <Button style={{margin:5}} title=" Resize " onPress={this.resize} />
            </View>
            <View style={styles.middle} onLayout={this.onLayout} >
              <Image style={{width:this.state.width, height:this.state.height, resizeMode:'stretch'}} source={this.state.imageSrc}/>
            </View>
            <View style={styles.bottom} >
              <Text>Status</Text>
            </View>
          </View>
        );
    }

    onLayout = (event) => {
        console.log('onLayout');
        let { width, height } = event.nativeEvent.layout;
    }

    resize = () => {

    }

    load = () => {
        console.log('ResizeImageScreen UploadImage');

        ImagePicker.showImagePicker(null, (response) => {
            console.log('Response = ', response);

            if (response.didCancel) {
                console.log('User cancelled image picker');
            } else if (response.error) {
                console.log('ImagePicker Error: ', response.error);
            } else if (response.customButton) {
                console.log('User tapped custom button: ', response.customButton);
            } else {
                let imageSrc = { uri: 'data:image/jpeg;base64,' + response.data };
                this.setState({imageSrc: imageSrc, width: response.width, height: response.height});
            }
        });
    }

}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        alignItems: 'stretch',
        justifyContent: 'center',
      },
      top: {
        flexDirection: 'row',
        justifyContent: 'center',
        padding: 5,
        backgroundColor: '#C0D0E0'
      },
      middle: {
        flex:1,
      },
      bottom: {
        backgroundColor: '#C0D0E0',
        height:20
      }
});
