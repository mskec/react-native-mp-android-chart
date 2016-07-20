import {PropTypes} from 'react';
import {
  requireNativeComponent,
  View
} from 'react-native';

import ChartBase from './ChartBase';
import ChartDataSetConfig from './ChartDataSetConfig';

const iface = {
  name: 'PieChart',
  propTypes: {
    ...ChartBase.propTypes,

    drawSliceText: PropTypes.bool,
    usePercentValues: PropTypes.bool,
    centerText: PropTypes.string,
    centerTextRadiusPercent: PropTypes.number,
    holeRadius: PropTypes.number,
    holeColor: PropTypes.string,
    transparentCircleRadius: PropTypes.number,
    transparentCircleColor: PropTypes.string,
    transparentCircleAlpha: PropTypes.number,
    maxAngle: PropTypes.number,

    // TODO PieChart should have only one dataset
    data: PropTypes.shape({
      datasets: PropTypes.arrayOf(PropTypes.shape({
        yValues: PropTypes.arrayOf(PropTypes.number),
        label: PropTypes.string,
        config: PropTypes.shape({
          ...ChartDataSetConfig.common,

          sliceSpace: PropTypes.number,
          selectionShift: PropTypes.number
        })
      })),
      xValues: PropTypes.arrayOf(PropTypes.string)
    })
  }
};

export default requireNativeComponent('MPAndroidPieChart', iface);
