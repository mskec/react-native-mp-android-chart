import {PropTypes} from 'react';
import {
  View
} from 'react-native';

import ChartBase from './ChartBase';
import {yAxisIface} from './YAxisIface';

const iface = {
  propTypes: {
    ...ChartBase.propTypes,

    drawGridBackground: PropTypes.bool,
    gridBackgroundColor: PropTypes.string,

    drawBorders: PropTypes.bool,
    borderColor: PropTypes.string,
    borderWidth: PropTypes.number,

    maxVisibleValueCount: PropTypes.number,
    autoScaleMinMaxEnabled: PropTypes.bool,
    keepPositionOnRotation: PropTypes.bool,

    scaleEnabled: PropTypes.bool,
    scaleXEnabled: PropTypes.bool,
    scaleYEnabled: PropTypes.bool,
    dragEnabled: PropTypes.bool,
    pinchZoom: PropTypes.bool,
    doubleTapToZoomEnabled: PropTypes.bool,

    yAxis: PropTypes.shape({
      left: PropTypes.shape(yAxisIface),
      right: PropTypes.shape(yAxisIface)
    })
  }
};

export default iface;
