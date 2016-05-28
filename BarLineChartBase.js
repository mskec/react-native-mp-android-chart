import {
  PropTypes,
  View
} from 'react-native';

import ChartBase from './ChartBase';

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
    keepPositionOnRotation: PropTypes.bool
  }
};

export default iface;
