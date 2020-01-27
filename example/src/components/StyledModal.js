import React from 'react';
import {
    Button,
    Modal,
    StyleSheet,
    Text,
    View,
} from 'react-native';

const StyledModal = props => {
    const {
        title,
        closeButtonLabel = "Close",
        visible,
        onPressClose,
        children
    } = props;

    return (
        <Modal
            animationType="fade"
            transparent={true}
            visible={visible}
            onRequestClose={() => {}}>
            <View
                style={{
                    ...StyleSheet.absoluteFillObject,
                    backgroundColor: 'rgba(0,0,0,0.5)',
                    flex: 1,
                    alignItems: 'center',
                    justifyContent: 'center',
                }}>
                <View
                    style={{
                        width: '80%',
                        minHeight: '50%',
                        maxHeight: '80%',
                        justifyContent: 'space-between',
                        backgroundColor: '#fff',
                    }}>
                    <View style={{margin: 20}}>
                        <Text
                            style={{
                                textAlign: 'center',
                                width: '100%',
                                fontWeight: 'bold',
                                fontSize: 20,
                            }}>
                            {title}
                        </Text>
                    </View>

                    {children}

                    <View style={{margin: 20}}>
                        <Button title={closeButtonLabel} onPress={onPressClose} />
                    </View>
                </View>
            </View>
        </Modal>
    );
};

export default StyledModal;
