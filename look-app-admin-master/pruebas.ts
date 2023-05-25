function createDeliveryRanges(): { [k: string]: DeliveryRangesEntity } {
    // Creates an object where the key is the start time of the slot.
    // The value is an object with the startTime, endTime and listPrice = 0.
    // We generate keys from 0 hours to 24 hours.
    return Array.from(Array(24).keys()).reduce(
      (
        previous: { [key: string]: DeliveryRangesEntity },
        current: number
      ): {
        [k: string]: DeliveryRangesEntity;
      } => {
        const startTime = `${current.toString().padStart(2, '0')}:00:00`;
        const endTime =
          current === 23
            ? '23:59:59'
            : `${(current + 1).toString().padStart(2, '0')}:00:00`;
        previous[startTime] = {
          startTime,
          endTime,
          listPrice: null
        };
        return previous;
      },
      {}
    );
  }


  function fillListPrices({
    day,
    firstTime,
    lastTime
  }: DayInfo): DayOfWeekForDeliveryEntity {
    if (day.deliveryRanges) {
      const deliveryRanges = day.deliveryRanges.map(
        (deliveryRange): DeliveryRangesEntity => {
          if (
            firstTime &&
            lastTime &&
            (deliveryRange.startTime < firstTime.startTime ||
              deliveryRange.startTime > lastTime.startTime)
          ) {
            const { listPrice } =
              deliveryRange.startTime < firstTime.startTime
                ? firstTime
                : lastTime;
            deliveryRange.listPrice = listPrice;
          }
          return deliveryRange;
        }
      );
      day.deliveryRanges = deliveryRanges.filter(
        (deliveryRange: DeliveryRangesEntity): boolean =>
          deliveryRange.listPrice !== null
      );
    }
    return day;
  }

  /**
   * Creates slots object to update VTex
   *
   * @param {DependencySlot[]} slots
   * @param {number} absoluteMoneyCost
   * @returns {DayOfWeekForDeliveryEntity[]}
   */
  function getUpdatedSlotsInfo(
    slots: DependencySlot[],
    absoluteMoneyCost: number,
    carrierId: string
  ): DayOfWeekForDeliveryEntity[] {
    const dayOfWeekForDeliveryMap = slots.reduce((previous, current): Map<
      number,
      DayInfo
    > => {
      if (current.active) {
        let { day, firstTime, lastTime } = previous.get(current.idDay) || {
          day: {
            dayOfWeek: current.idDay,
            deliveryRanges: Object.values(createDeliveryRanges())
          },
          firstTime: null,
          lastTime: null
        };

        if (day && day.deliveryRanges) {
          const index = day.deliveryRanges.findIndex(
            (d: DeliveryRangesEntity): boolean =>
              d.startTime === current.startHour
          );
          if (index >= 0) {
            day.deliveryRanges[index].listPrice =
              carrierId === 'CompraRecoge_Programado'
                ? 0
                : current.shippingCostWeb
                ? current.shippingCostWeb - absoluteMoneyCost
                : null;

            firstTime =
              firstTime === null
                ? day.deliveryRanges[index]
                : firstTime.startTime < current.startHour
                ? firstTime
                : day.deliveryRanges[index];

            lastTime =
              lastTime === null
                ? day.deliveryRanges[index]
                : lastTime.startTime > current.endHour
                ? lastTime
                : day.deliveryRanges[index];
          }
        }
        previous.set(current.idDay, { day, firstTime, lastTime });
      }
      return previous;
    }, new Map<number, DayInfo>());

    return [...dayOfWeekForDeliveryMap.values()].map(fillListPrices);
  }
